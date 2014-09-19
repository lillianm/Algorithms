package thumbtack;

public interface SimpleDatabase {
	public abstract void set(String key, int value);
	public abstract void unset(String key);
	public abstract Integer get(String key);
	public abstract int numeqto(int value);
	public abstract void modifyInverseCount(int value,int n);
}
