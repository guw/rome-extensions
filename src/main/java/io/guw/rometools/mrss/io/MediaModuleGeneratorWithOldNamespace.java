package io.guw.rometools.mrss.io;

import java.util.Collections;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.MediaModule;
import com.rometools.modules.mediarss.types.Category;
import com.rometools.modules.mediarss.types.Credit;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import com.rometools.modules.mediarss.types.Metadata;
import com.rometools.modules.mediarss.types.PlayerReference;
import com.rometools.modules.mediarss.types.Rating;
import com.rometools.modules.mediarss.types.Restriction;
import com.rometools.modules.mediarss.types.Text;
import com.rometools.modules.mediarss.types.Thumbnail;
import com.rometools.modules.mediarss.types.UrlReference;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

import io.guw.rometools.mrss.MediaModuleWithOldNamespaceConstants;

public class MediaModuleGeneratorWithOldNamespace implements ModuleGenerator {

	private static final Namespace NS = Namespace.getNamespace("mediaold", MediaModuleWithOldNamespaceConstants.URI);
	private static final Set<Namespace> NAMESPACES = Collections.singleton(NS);

	protected void addNotNullAttribute(final Element target, final String name, final Object value) {
		if ((target == null) || (value == null)) {
			return;
		} else {
			target.setAttribute(name, value.toString());
		}
	}

	protected Element addNotNullElement(final Element target, final String name, final Object value) {
		if (value == null) {
			return null;
		} else {
			final Element e = generateSimpleElement(name, value.toString());
			target.addContent(e);

			return e;
		}
	}

	@Override
	public void generate(final Module module, final Element element) {
		if (module instanceof MediaModule) {
			final MediaModule m = (MediaModule) module;
			generateMetadata(m.getMetadata(), element);
			generatePlayer(m.getPlayer(), element);
		}

		if (module instanceof MediaEntryModule) {
			final MediaEntryModule m = (MediaEntryModule) module;
			final MediaGroup[] g = m.getMediaGroups();

			for (final MediaGroup element2 : g) {
				generateGroup(element2, element);
			}

			final MediaContent[] c = m.getMediaContents();

			for (final MediaContent element2 : c) {
				generateContent(element2, element);
			}
		}
	}

	public void generateContent(final MediaContent c, final Element e) {
		final Element mc = new Element("content", NS);
		addNotNullAttribute(mc, "medium", c.getMedium());
		addNotNullAttribute(mc, "channels", c.getAudioChannels());
		addNotNullAttribute(mc, "bitrate", c.getBitrate());
		addNotNullAttribute(mc, "duration", c.getDuration());
		addNotNullAttribute(mc, "expression", c.getExpression());
		addNotNullAttribute(mc, "fileSize", c.getFileSize());
		addNotNullAttribute(mc, "framerate", c.getFramerate());
		addNotNullAttribute(mc, "height", c.getHeight());
		addNotNullAttribute(mc, "lang", c.getLanguage());
		addNotNullAttribute(mc, "samplingrate", c.getSamplingrate());
		addNotNullAttribute(mc, "type", c.getType());
		addNotNullAttribute(mc, "width", c.getWidth());

		if (c.isDefaultContent()) {
			addNotNullAttribute(mc, "isDefault", "true");
		}

		if (c.getReference() instanceof UrlReference) {
			addNotNullAttribute(mc, "url", c.getReference());
			generatePlayer(c.getPlayer(), mc);
		} else {
			generatePlayer(c.getPlayer(), mc);
		}

		generateMetadata(c.getMetadata(), mc);
		e.addContent(mc);
	}

	public void generateGroup(final MediaGroup g, final Element e) {
		final Element t = new Element("group", NS);
		final MediaContent[] c = g.getContents();

		for (final MediaContent element : c) {
			generateContent(element, t);
		}

		generateMetadata(g.getMetadata(), t);
		e.addContent(t);
	}

	public void generateMetadata(final Metadata m, final Element e) {
		if (m == null) {
			return;
		}

		final Category[] cats = m.getCategories();

		for (final Category cat : cats) {
			final Element c = generateSimpleElement("category", cat.getValue());
			addNotNullAttribute(c, "scheme", cat.getScheme());
			addNotNullAttribute(c, "label", cat.getLabel());
			e.addContent(c);
		}

		final Element copyright = addNotNullElement(e, "copyright", m.getCopyright());
		addNotNullAttribute(copyright, "url", m.getCopyrightUrl());

		final Credit[] creds = m.getCredits();

		for (final Credit cred : creds) {
			final Element c = generateSimpleElement("credit", cred.getName());
			addNotNullAttribute(c, "role", cred.getRole());
			addNotNullAttribute(c, "scheme", cred.getScheme());
			e.addContent(c);
		}

		final Element desc = addNotNullElement(e, "description", m.getDescription());
		addNotNullAttribute(desc, "type", m.getDescriptionType());

		if (m.getHash() != null) {
			final Element hash = addNotNullElement(e, "hash", m.getHash().getValue());
			addNotNullAttribute(hash, "algo", m.getHash().getAlgorithm());
		}

		final String[] keywords = m.getKeywords();

		if (keywords.length > 0) {
			String keyword = keywords[0];

			for (int i = 1; i < keywords.length; i++) {
				keyword += ", " + keywords[i];
			}

			addNotNullElement(e, "keywords", keyword);
		}

		final Rating[] rats = m.getRatings();

		for (final Rating rat2 : rats) {
			final Element rat = addNotNullElement(e, "rating", rat2.getValue());
			addNotNullAttribute(rat, "scheme", rat2.getScheme());

			if (rat2.equals(Rating.ADULT)) {
				addNotNullElement(e, "adult", "true");
			} else if (rat2.equals(Rating.NONADULT)) {
				addNotNullElement(e, "adult", "false");
			}
		}

		final Text[] text = m.getText();

		for (final Text element : text) {
			final Element t = addNotNullElement(e, "text", element.getValue());
			addNotNullAttribute(t, "type", element.getType());
			addNotNullAttribute(t, "start", element.getStart());
			addNotNullAttribute(t, "end", element.getEnd());
		}

		final Thumbnail[] thumbs = m.getThumbnail();

		for (final Thumbnail thumb : thumbs) {
			final Element t = new Element("thumbnail", NS);
			addNotNullAttribute(t, "url", thumb.getUrl());
			addNotNullAttribute(t, "width", thumb.getWidth());
			addNotNullAttribute(t, "height", thumb.getHeight());
			addNotNullAttribute(t, "time", thumb.getTime());
			e.addContent(t);
		}

		final Element title = addNotNullElement(e, "title", m.getTitle());
		addNotNullAttribute(title, "type", m.getTitleType());

		final Restriction[] r = m.getRestrictions();

		for (final Restriction element : r) {
			final Element res = addNotNullElement(e, "restriction", element.getValue());
			addNotNullAttribute(res, "type", element.getType());
			addNotNullAttribute(res, "relationship", element.getRelationship());
		}
	}

	public void generatePlayer(final PlayerReference p, final Element e) {
		if (p == null) {
			return;
		}

		final Element t = new Element("player", NS);
		addNotNullAttribute(t, "url", p.getUrl());
		addNotNullAttribute(t, "width", p.getWidth());
		addNotNullAttribute(t, "height", p.getHeight());
		e.addContent(t);
	}

	protected Element generateSimpleElement(final String name, final String value) {
		final Element element = new Element(name, NS);
		element.addContent(value);

		return element;
	}

	@Override
	public Set<Namespace> getNamespaces() {
		return NAMESPACES;
	}

	@Override
	public String getNamespaceUri() {
		return MediaModuleWithOldNamespaceConstants.URI;
	}

}
