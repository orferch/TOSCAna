package org.opentosca.toscana.core.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.opentosca.toscana.core.api.PlatformController;
import org.opentosca.toscana.core.api.CsarTransformationController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class TransformationResponse extends ResourceSupport {
	private int progress;
	private String status;
	private String platform;
	private String csarName;

	public TransformationResponse(
		@JsonProperty("progress") int progress,
		@JsonProperty("status") String status,
		String platform,
		String csarName
	) {
		this.progress = progress;
		this.status = status;
		this.csarName = csarName;
		this.platform = platform;
		this.add(linkTo(methodOn(CsarTransformationController.class)
			.getCSARTransformation(csarName,platform)).withSelfRel());
		this.add(linkTo(methodOn(CsarTransformationController.class)
			.getTransformationLogs(csarName,platform, 0L)).withRel("logs"));
		this.add(linkTo(methodOn(PlatformController.class)
			.getPlatform(platform)).withRel("platform"));
		this.add(linkTo(methodOn(CsarTransformationController.class)
			.getTransformationArtifact(csarName, platform)).withRel("artifact"));
		this.add(linkTo(methodOn(CsarTransformationController.class)
			.getTransformationProperties(csarName, platform)).withRel("properties"));
		this.add(linkTo(methodOn(CsarTransformationController.class)
			.deleteTransformation(csarName,platform)).withRel("delete"));
	}

	@JsonProperty("progress")
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}