package binarysearchtree;
/**
 * Class to hold data for a Huffman Node.
 * 
 * @author Lisa Miller
 * @since 11/23/25
 */
public class HuffmanNodeData implements Comparable<HuffmanNodeData> {
    /** Character stored in the node. */
    char character;  
    /** Frequency of the character. */  
    int frequency;  
    /**  
     * Constructor to initialize character and frequency.  
     *  
     * @param character the character  
     * @param frequency the frequency of the character  
     */
    public HuffmanNodeData(char character, int frequency) {  
        this.character = character;  
        this.frequency = frequency;  
    }  
    /**
     * Accessor for character.
     * @return the character stored in the node 
     */
    public char getCharacter() {  
        return character;  
    }  
    
    /**
     * Accessor for frequency.
     * @return the frequency of the character
     */
    public int getFrequency() {  
        return frequency;  
    }  
    
    /**
     * Mutator for frequency.
     * @param frequency the new frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    /**
     * toString method to display character and frequency.
     * @return string representation of the HuffmanNodeData
     */
    @Override  
    public String toString() {  
        return character + ":" + frequency;  
    }
    /**
     * compareTo method to compare based on frequency.
     * @param other the other HuffmanNodeData to compare to
     * @return negative if this frequency is less, positive if greater, zero if equal
     */
    @Override
    public int compareTo(HuffmanNodeData other) {
        return Integer.compare(this.frequency, other.frequency);
    }

}
