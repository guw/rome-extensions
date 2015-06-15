package io.guw.rometools.mrss.io;

import java.util.Collections;
import java.util.Set;

import org.jdom2.Namespace;

import com.rometools.modules.mediarss.io.MediaModuleGenerator;

import io.guw.rometools.mrss.MediaModuleWithOldNamespaceConstants;

public class MediaModuleGeneratorWithOldNamespace extends MediaModuleGenerator {

	private static final Namespace NS = Namespace.getNamespace("mediaold", MediaModuleWithOldNamespaceConstants.URI);
	private static final Set<Namespace> NAMESPACES = Collections.singleton(NS);

	@Override
	public Set<Namespace> getNamespaces() {
		return NAMESPACES;
	}

	@Override
	public String getNamespaceUri() {
		return MediaModuleWithOldNamespaceConstants.URI;
	}

}
