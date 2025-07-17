package com.example.demo.util;

import java.util.List;
import java.util.Map;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class Markdown {
	private final Parser parser;
	private final HtmlRenderer renderer;
	
	public Markdown() {
		List<Extension> extensions = List.of(TablesExtension.create());
		parser = Parser.builder()
		        .extensions(extensions)
		        .build();
		renderer = HtmlRenderer.builder()
		        .extensions(extensions)
		        .attributeProviderFactory((ctx) -> new TableClassAttributeProvider())
		        .build();
	}
	
	public String renderHTML(String markdownText) {
		return renderer.render(parser.parse(markdownText));
	}
	
	private static class TableClassAttributeProvider implements AttributeProvider {
		@Override
		public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
			if (tagName.equals("table")) {
				String existingClasses = attributes.get("class");
				if (existingClasses == null || existingClasses.isEmpty()) {
                    attributes.put("class", "table table-bordered table-striped w-auto");
                } else {
                    attributes.put("class", existingClasses + " table table-bordered table-striped w-auto");
                }
			}
			
		}
	}
}
