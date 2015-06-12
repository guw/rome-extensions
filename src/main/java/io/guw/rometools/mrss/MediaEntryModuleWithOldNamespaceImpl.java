package io.guw.rometools.mrss;

import com.rometools.modules.mediarss.MediaEntryModuleImpl;

public class MediaEntryModuleWithOldNamespaceImpl extends MediaEntryModuleImpl {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Override
	public String getUri() {
		return MediaModuleWithOldNamespaceConstants.URI;
	}

}
