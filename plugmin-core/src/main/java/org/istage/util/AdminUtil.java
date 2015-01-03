package org.istage.util;

import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase;
import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;
import static org.istage.util.AnnotationUtils.getAnnotationsByType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openxava.annotations.extended.Label;
import org.openxava.tab.meta.MetaTab;
import org.openxava.util.meta.MetaElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.SourceFile;

public class AdminUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminUtil.class);

	public static final String PLUGMIN_REST_BASE_URL = "/plugmin";

	public static String titleFromFieldName(String title) {
		title = title.replaceAll("_", " ");
		title = title.replaceAll("\\.", " ");
		String[] splitCamel = splitByCharacterTypeCamelCase(title);
		List<String> list = new ArrayList<String>();
		for (String token : splitCamel) {
			token = token.toLowerCase();
			if (!list.contains(token))
				list.add(token);
		}
		title = join(list, ' ');
		title = title.replaceAll("\\s+", " ");
		title = capitalizeFully(title);
		return title;
	}

	public static String titleFromLabelAnnotation(Field field) {
		String title = null;
		Label label = field.getAnnotation(Label.class);
		if (label != null) {
			title = label.value();
		}
		return title;
	}

	public static String titleFromLabelAnnotation(Field field,
			MetaElement metaElement) {
		if (metaElement instanceof MetaTab) {
			MetaTab tab = (MetaTab) metaElement;

			Label[] labels = getAnnotationsByType(field, Label.class);
			
			for (Label label : labels) {
				if (label != null) {
					String[] eligibleTabs = label.forTabs();
					if (eligibleTabs.length == 0) {
						return label.value();
					} else {
						for (String eligibleTab : eligibleTabs) {
							if (tab.getName().equals(eligibleTab)) {
								return label.value();
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static void memoryUsage() {
		// Get current size of heap in bytes
		long heapSize = Runtime.getRuntime().totalMemory();

		// Get maximum size of heap in bytes. The heap cannot grow beyond this
		// size.
		// Any attempt will result in an OutOfMemoryException.
		long heapMaxSize = Runtime.getRuntime().maxMemory();

		// Get amount of free memory within the heap in bytes. This size will
		// increase
		// after garbage collection and decrease as new objects are created.
		long heapFreeSize = Runtime.getRuntime().freeMemory();

		logger.info("Heap Size: " + humanReadableByteCount(heapSize, false));
		logger.info("Heap Max Size: "
				+ humanReadableByteCount(heapMaxSize, false));
		logger.info("Heap Free Size: "
				+ humanReadableByteCount(heapFreeSize, false));
	}

	public static String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1)
				+ (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	/**
	 * @param code
	 *            JavaScript source code to compile.
	 * @return The compiled version of the code.
	 */
	public static String prettify(String code) {
		com.google.javascript.jscomp.Compiler compiler = new com.google.javascript.jscomp.Compiler();

		CompilerOptions options = new CompilerOptions();
		options.prettyPrint = true;

		CompilationLevel.WHITESPACE_ONLY
				.setOptionsForCompilationLevel(options);

		// To get the complete set of externs, the logic in
		// CompilerRunner.getDefaultExterns() should be used here.
		SourceFile extern = SourceFile.fromCode("externs.js",
				"function alert(x) {}");

		// The dummy input name "input.js" is used here so that any warnings or
		// errors will cite line numbers in terms of input.js.
		SourceFile input = SourceFile.fromCode("input.js", code);

		// compile() returns a Result, but it is not needed here.
		compiler.compile(extern, input, options);

		// The compiler is responsible for generating the compiled code; it is
		// not
		// accessible via the Result.
		return compiler.toSource();
	}

	public static void main(String a[]) {
		String fnName = "dropdown_editor";
		String json = "{}";
		String fn = "function "
				+ fnName
				+ "(container, options) {"
				+ "var input = $('<input data-bind=\"value:' + options.field.split('\\.')[0] + '\"/>');"
				+ "input.attr('name', options.field.split('\\.')[0]);"
				+ "input.attr('id', options.field.split('\\.')[0]);"
				+ "input.appendTo(container);" + "input.kendoDropDownList("
				+ json + ")" + "}";
		String compile = prettify(fn);
		System.out.println(compile);
	}
}
