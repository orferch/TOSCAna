package org.opentosca.toscana.core.api;

import org.opentosca.toscana.core.api.model.PlatformResponse;
import org.opentosca.toscana.core.transformation.Platform;
import org.opentosca.toscana.core.util.PlatformProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

	@Autowired
	public PlatformProvider platformProvider;

	@GetMapping
	public ResponseEntity<ResourceSupport> getPlatforms() {
		Link selfLink = linkTo(methodOn(PlatformController.class).getPlatforms()).withSelfRel();
		ArrayList<PlatformResponse> responses = new ArrayList<>();
		for (Platform platform : platformProvider.getSupportedPlatforms()) {
			PlatformResponse res = getPlatformResource(platform);
			responses.add(res);
		}
		Resources<PlatformResponse> resources = new Resources<>(responses, selfLink);
		
		return ResponseEntity.ok(resources);
	}

	private PlatformResponse getPlatformResource(Platform platform) {
		PlatformResponse res = new PlatformResponse(platform);
		res.add(linkTo(methodOn(PlatformController.class).getPlatform(res.getIdentifier())).withSelfRel());
		return res;
	}

	@RequestMapping("/{id}")
	@GetMapping
	public ResponseEntity<PlatformResponse> getPlatform(
		@PathVariable(name = "id") String id
	) {
		Platform p = platformProvider.findById(id);
		if(p == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(getPlatformResource(p));
	}
}