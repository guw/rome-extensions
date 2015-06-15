package io.guw.rometools.mrss;

import java.io.Serializable;

import com.rometools.modules.mediarss.MediaModule;
import com.rometools.modules.mediarss.types.Metadata;
import com.rometools.modules.mediarss.types.PlayerReference;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.ModuleImpl;

public class MediaModuleWithOldNamespaceImpl extends ModuleImpl implements MediaModule, Serializable {

	private static final long serialVersionUID = 1L;

	private Metadata metadata;
	private PlayerReference player;

	private final Class<? extends MediaModule> beanClass;

	public MediaModuleWithOldNamespaceImpl() {
		this(MediaModule.class, MediaModuleWithOldNamespaceConstants.URI);
	}

	protected MediaModuleWithOldNamespaceImpl(Class<? extends MediaModule> beanClass, String uri) {
		super(beanClass, uri);
		this.beanClass = beanClass;
	}

	@Override
	public Object clone() {
		final MediaModuleWithOldNamespaceImpl m = new MediaModuleWithOldNamespaceImpl();
		m.setMetadata((Metadata) metadata.clone());
		m.setPlayer(player);

		return m;
	}

	@Override
	public void copyFrom(final CopyFrom obj) {
		final MediaModule m = (MediaModule) obj;
		metadata = (Metadata) m.getMetadata().clone();
		player = m.getPlayer();
	}

	@Override
	public Class<? extends CopyFrom> getInterface() {
		return beanClass;
	}

	/**
	 * Metadata for a feed.
	 *
	 * @return Metadata for a feed.
	 */
	@Override
	public Metadata getMetadata() {
		return metadata;
	}

	/**
	 * Player for a feed.
	 *
	 * @return Player for a feed.
	 */
	@Override
	public PlayerReference getPlayer() {
		return player;
	}

	/**
	 * Metadata for a feed.
	 *
	 * @param metadata
	 *            Metadata for a feed.
	 */
	public void setMetadata(final Metadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * Player for a feed.
	 *
	 * @param player
	 *            Player for a feed.
	 */
	public void setPlayer(final PlayerReference player) {
		this.player = player;
	}
}