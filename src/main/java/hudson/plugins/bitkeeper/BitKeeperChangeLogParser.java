package hudson.plugins.bitkeeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

import org.xml.sax.SAXException;

import hudson.model.AbstractBuild;
import hudson.scm.ChangeLogParser;
import hudson.scm.ChangeLogSet;
import hudson.scm.ChangeLogSet.Entry;

public class BitKeeperChangeLogParser extends ChangeLogParser {

	@Override
	public ChangeLogSet<? extends Entry> parse(AbstractBuild build,
			File changelogFile) throws IOException, SAXException {
		BufferedReader changelog = null;
		ArrayList<BitKeeperChangeset> changes = new ArrayList<BitKeeperChangeset>();
		BitKeeperChangeset cset;
		try { 
			changelog = new BufferedReader(new FileReader(changelogFile));
			String line;
			cset = null;
			while((line = changelog.readLine()) !=null) {
				if(line.startsWith("U ")) {
					if(cset != null) {
						changes.add(cset);
					}
					cset = new BitKeeperChangeset(line.substring(2));
				} else if(line.startsWith("C ")) {
					cset.addComment(line.substring(2));
				} else if(line.startsWith("T ")) {
					cset.addTag(line.substring(2));
				} else if(line.startsWith("F ")) {
					cset.addPath(line.substring(2));
				}
			}
		} finally {
			if(changelog != null)
				changelog.close();
		}
		// grab the last cset
		if(cset != null) {
			changes.add(cset);
		}
		return new BitKeeperChangeSetList(build, changes);
	}

}
