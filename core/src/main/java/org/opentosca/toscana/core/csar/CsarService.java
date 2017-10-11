package org.opentosca.toscana.core.csar;

import java.io.InputStream;
import java.util.List;

public interface CsarService {


    /**
     * Creates a new Csar instance
     * @param identifier identifying name of csar, must match [a-z0-9_-]+
     * @param csarStream the actual cloud service archive as InputStream
     * @return the newly created Csar instance
     */
    Csar submitCsar(String identifier, InputStream csarStream);

    /**
     * Deletes given Csar and all associated transformations from in-memory and persistence layer.
     * @return true if successful, false otherwise
     */
    void deleteCsar(Csar csar);

    /**
     * Returns all Csars currently managed by the application
     * @return
     */
    List<Csar> getCsars();

	/**
	 * Returns csar which identifier field matches given identifier.
	 * Returns null if no match is found
	 */
	Csar getCsar(String identifier);
}