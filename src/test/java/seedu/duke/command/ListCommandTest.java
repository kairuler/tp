package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import seedu.duke.exceptions.NullFolderException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.FolderNotFoundException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.exceptions.RepeatedIdException;


import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.NUSNet;
import seedu.duke.secrets.StudentID;
import seedu.duke.secrets.WifiPassword;

import seedu.duke.storage.SecretMaster;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * JUnit tests for the ListCommand class.
 */
public class ListCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setStream() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    /**
     * Tests the isExit method when there is no folder name.
     */
    @Test
    void isExit_noFolder() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("unnamed");
        ListCommand listCommand = new ListCommand("list", folders);
        assertFalse(listCommand.isExit());
    }

    /**
     * Tests the isExit method when there is a folder name.
     */
    @Test
    void isExit_withFolder() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("Folder123");
        ListCommand listCommand = new ListCommand("list f/Folder123", folders);
        assertFalse(listCommand.isExit());
    }

    /**
     * Tests the extractFolderName method when there is no folder name.
     */
    @Test
    void getList_noFolder() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("unnamed");
        ListCommand listCommand = new ListCommand("list", folders);
        assertEquals(listCommand.extractFolderName("list"), null);
    }

    /**
     * Tests the extractFolderName method when there is a folder name.
     */
    @Test
    void getList_withFolder() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("Folder123");
        ListCommand listCommand = new ListCommand("list f/Folder123", folders);
        assertEquals(listCommand.extractFolderName("list f/Folder123"), "Folder123");
    }

    /**
     * Tests the maskStringPassword method.
     */
    @Test
    void testMaskStringPassword() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("unnamed");
        ListCommand listCommand = new ListCommand("list", folders);
        String maskedPassword = listCommand.maskStringPassword("password");
        assertEquals("********", maskedPassword);
    }

    /**
     * Tests the maskIntPasswordAsString method.
     */
    @Test
    void testMaskIntPasswordAsString() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("unnamed");
        ListCommand listCommand = new ListCommand("list", folders);
        String masked1234 = listCommand.maskIntPasswordAsString(1234);
        String masked5678 = listCommand.maskIntPasswordAsString(5678);

        assertEquals("****", masked1234);
        assertEquals("****", masked5678);
    }

    /**
     * Tests the ListCommand method for different secret types.
     */
    @Test
    void testSecretTypes() {
        SecretMaster secretMaster = new SecretMaster();

        try {
            secretMaster.addSecret(new BasicPassword("BasicPassword1", "Username1",
                    "Password1", "Url1.com"));
            secretMaster.addSecret(new CreditCard("CreditCard1", "John Doe",
                    "1234 5678 1234 5678", "123", "12/25"));
            secretMaster.addSecret(new CryptoWallet("CryptoWallet1", "Folder1",
                    "DeepsD", "PrivateKey1", "SeedPhrase1"));
            secretMaster.addSecret(new NUSNet("NUSNet1", "NUSNetId1",
                    "Password1"));
            secretMaster.addSecret(new StudentID("StudentID1",
                    "StudentID1"));
            secretMaster.addSecret(new WifiPassword("WifiPassword1", "Username1",
                    "Password1"));
            HashSet<String> folders = new HashSet<>();
            folders.add("unnamed");
            ListCommand listCommand = new ListCommand("list", folders);
            listCommand.execute(secretMaster);

        } catch (FolderExistsException | IllegalFolderNameException | IllegalSecretNameException | RepeatedIdException |
                 InvalidCreditCardNumberException | InvalidExpiryDateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FolderNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullFolderException e) {
            throw new RuntimeException(e);
        }
    }
}
