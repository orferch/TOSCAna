package org.opentosca.toscana.core.api.dummy;

import org.opentosca.toscana.core.csar.Csar;
import org.opentosca.toscana.core.transformation.Platform;
import org.opentosca.toscana.core.transformation.Transformation;
import org.opentosca.toscana.core.transformation.TransformationService;
import org.springframework.stereotype.Service;

@Service //TODO If Transformation Service has been implemented
public class DummyTransformationService implements TransformationService {
	@Override
	public void createTransformation(Csar csar, Platform targetPlatform) {
		System.out.println("Creating Transformation for " + csar.getIdentifier() + " on " + targetPlatform.id);
		csar.getTransformations().put(targetPlatform.id, new DummyTransformation(targetPlatform));
	}

	@Override
	public boolean startTransformation(Transformation transformation) {
		return false;
	}

	@Override
	public boolean abortTransformation(Transformation transformation) {
		return false;
	}

	@Override
	public boolean deleteTransformation(Transformation transformation) {
		
		return false;
	}
}