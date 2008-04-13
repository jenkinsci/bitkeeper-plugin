package hudson.plugins.bitkeeper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import hudson.model.User;
import hudson.scm.ChangeLogSet;

public class BitKeeperChangeset extends ChangeLogSet.Entry {
	private List<String> affectedPaths;
	private User user;
	private StringBuilder comment;
	
	public BitKeeperChangeset(String user) {
		this.user = User.get(user);
		this.comment = new StringBuilder();
		this.affectedPaths = new ArrayList<String>();
	}

	@Override
	public Collection<String> getAffectedPaths() {
		return affectedPaths;
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
		comment.append(c);
	}
	
	public void addPath(String p) {
		affectedPaths.add(p);
	}

}
