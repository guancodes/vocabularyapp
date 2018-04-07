package vocabularyapp;

/**
 * this interface having following methods was introduced to mock unit test
 * @author guanwang
 */
public interface DictScannable {

    /**
     * Checks if this DictScannable has next element
     * @return if has next element
     */
    public boolean hasNext();

    /**
     * Gets the next line of the DictScannable
     * @return the next line
     */
    public String nextLine();
}
