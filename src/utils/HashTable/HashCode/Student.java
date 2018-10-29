package utils.HashTable.HashCode;

public class Student {
	
	private int gra;
	private int cls;
	private String firstName;
	private String lastName;
	
	public Student(int gra, int cls, String firstName, String lastName) {
		this.gra = gra;
		this.cls = cls;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		int B = 31;
		int hash = 0;
		hash = hash * B + gra;
		hash = hash * B + cls;
		hash = hash * B + firstName.toLowerCase().hashCode();
		hash = hash * B + lastName.toLowerCase().hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)		// 当前的类对象和传入的o是否为同一个引用
			return true;
		if(o == null)
			return false;
		if(getClass() != o.getClass())
			return false;
		
		// 前面的三个基础检查之后，可以放心的将o转化成实体Student对象
		Student another = (Student)o;
		return this.gra == another.gra &&
				this.cls == another.cls &&
				this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
				this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
	}
	
		
}
