package old;

class Category {
	private int id;
	private String title;
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
				this.title = title.replaceAll("[^\\u0000-\\uFFFF]", "");
;
	}
	
	public String getTitle() {
		return title;
	}
	public int getId() {
		return id;
	}
}