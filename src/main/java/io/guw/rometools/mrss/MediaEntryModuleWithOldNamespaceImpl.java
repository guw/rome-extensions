package io.guw.rometools.mrss;

import java.io.Serializable;

import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import com.rometools.modules.mediarss.types.Metadata;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

public class MediaEntryModuleWithOldNamespaceImpl extends MediaModuleWithOldNamespaceImpl implements MediaEntryModule, Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private MediaContent[] mediaContents = new MediaContent[0];
	private MediaGroup[] mediaGroups = new MediaGroup[0];

	/**
	 * Creates a new instance.
	 */
	public MediaEntryModuleWithOldNamespaceImpl() {
		super(MediaEntryModule.class, MediaModuleWithOldNamespaceConstants.URI);
	}

	@Override
	public Object clone() {
		final MediaEntryModuleWithOldNamespaceImpl m = new MediaEntryModuleWithOldNamespaceImpl();
		m.setMediaContents(mediaContents.clone());
		m.setMediaGroups(mediaGroups.clone());
		m.setMetadata(getMetadata() == null ? null : (Metadata) getMetadata().clone());
		m.setPlayer(getPlayer());

		return m;
	}

	@Override
	public void copyFrom(final CopyFrom obj) {
		MediaEntryModuleWithOldNamespaceImpl other = (MediaEntryModuleWithOldNamespaceImpl) obj;
		other = (MediaEntryModuleWithOldNamespaceImpl) other.clone();
		setMediaContents(other.getMediaContents());
		setMediaGroups(other.getMediaGroups());
		setMetadata(other.getMetadata());
		setPlayer(other.getPlayer());
	}

	@Override
	public boolean equals(final Object obj) {
		final EqualsBean eBean = new EqualsBean(MediaEntryModuleWithOldNamespaceImpl.class, this);

		return eBean.beanEquals(obj);
	}

	/**
	 * MediaContent items for the entry
	 *
	 * @return MediaContent items for the entry
	 */
	@Override
	public MediaContent[] getMediaContents() {
		return mediaContents;
	}

	/**
	 * MediaGroups for the entry
	 *
	 * @return MediaGroups for the entry
	 */
	@Override
	public MediaGroup[] getMediaGroups() {
		return mediaGroups;
	}

	@Override
	public int hashCode() {
		final EqualsBean equals = new EqualsBean(MediaEntryModuleWithOldNamespaceImpl.class, this);

		return equals.beanHashCode();
	}

	/**
	 * MediaContent items for the entry
	 *
	 * @param mediaContents
	 *            MediaContent items for the entry
	 */
	public void setMediaContents(final MediaContent[] mediaContents) {
		this.mediaContents = mediaContents == null ? new MediaContent[0] : mediaContents;
	}

	/**
	 * MediaGroups for the entry
	 *
	 * @param mediaGroups
	 *            MediaGroups for the entry
	 */
	public void setMediaGroups(final MediaGroup[] mediaGroups) {
		this.mediaGroups = mediaGroups == null ? new MediaGroup[0] : mediaGroups;
	}

	@Override
	public String toString() {
		final ToStringBean tsBean = new ToStringBean(MediaEntryModuleWithOldNamespaceImpl.class, this);

		return tsBean.toString();
	}
}