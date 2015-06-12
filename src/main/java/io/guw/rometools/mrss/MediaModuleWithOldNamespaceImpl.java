package io.guw.rometools.mrss;

import com.rometools.modules.mediarss.MediaModuleImpl;

public class MediaModuleWithOldNamespaceImpl extends MediaModuleImpl {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Override
	public String getUri() {
		return MediaModuleWithOldNamespaceConstants.URI;
	}
}
