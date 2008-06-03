package hudson.plugins.bitkeeper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import hudson.model.User;
import hudson.scm.ChangeLogSet;
import hudson.scm.EditType;

public class BitKeeperChangeset extends ChangeLogSet.Entry {
	private List<String> affectedPaths;
	private List<String> tags;
	private User user;
	private StringBuilder comment;
	
	public BitKeeperChangeset(String user) {
		this.user = User.get(user);
		this.comment = new StringBuilder();
		this.tags = new ArrayList<String>();
		this.affectedPaths = new ArrayList<String>();
	}

	@Override
	public Collection<String> getAffectedPaths() {
		return affectedPaths;
	}

	public Collection<String> getTags() {
		return tags;
	}
	
	public EditType getEditType() {
		return EditType.EDIT;
	}
	
	@Override
	public User getAuthor() {
		return user;
	}

	@Override
	public String getMsg() {
		return comment.toString();
	}
	
	public void setParent(ChangeLogSet p) {
		super.setParent(p);
	}
	
	public void addComment(String c) {
		if(comment.length() > 0) {
			comment.append("\n");
		}
		comment.append(c);
	}
	
	public void addTag(String t) {
		tags.add(t);
	}
	
	public void addPath(String p) {
		affectedPaths.add(p);
	}

}
