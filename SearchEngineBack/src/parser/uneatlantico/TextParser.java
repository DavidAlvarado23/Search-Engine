package parser.uneatlantico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import entities.uneatlantico.InvertedIndex;
import entities.uneatlantico.TermFrecuency;

public class TextParser {

	/**
	 * Parse el texto de un documento completo.
	 * 
	 * @param text
	 *            Cadena de caracteres que contiene un documento entero.
	 * @return Lista de objetos de tipo InvertedIndex con las estadisticas del
	 *         documento.
	 */
	public static List<InvertedIndex> parseText(String text) {
		List<InvertedIndex> parsedText = new ArrayList<>();

		List<String> completeLine = new ArrayList<String>(Arrays.asList(text.split("\\n")));
		completeLine.forEach(line -> {
			List<String> wordsInLine = new ArrayList<String>(Arrays.asList(line.split("[\\s\\.]")));
			wordsInLine.forEach(word -> {
				final Pattern pattern = Pattern.compile("[^a-zA-ZáéíóúÁÉÍÓÚ]", Pattern.MULTILINE);
				final Matcher matcher = pattern.matcher(word);
				String result = matcher.replaceAll("");
				if (!result.equals("")) {
					if (!parsedText.stream().anyMatch(x -> x.getWord().equals(result))) {
						List<Integer> occurance = new ArrayList<Integer>();
						occurance.add(1);
						parsedText.add(new InvertedIndex(result, new TermFrecuency(1, occurance)));
					} else {
						Stream<InvertedIndex> found = parsedText.stream().filter(x -> x.getWord().equals(result));
						found.forEach(foundWord -> {
							int occurance = foundWord.getStats().getAppearance();
							foundWord.getStats().setAppearance(++occurance);
						});
					}
				}
			});
		});

		return parsedText;
	}

}
