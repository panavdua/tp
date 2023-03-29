package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.LeaveCounter;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Payroll;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_EMPLOYEE_ID = "Employee ID is not valid.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code EmployeeId} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified employee ID is invalid.
     */
    public static EmployeeId parseEmployeeId(String employeeId) throws ParseException {
        String trimmedEmployeeId = employeeId.trim();
        if (!EmployeeId.isValidEmployeeId(trimmedEmployeeId)) {
            throw new ParseException(MESSAGE_INVALID_EMPLOYEE_ID);
        }
        return new EmployeeId(trimmedEmployeeId);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String Array nameKeywords} into a boolean indicating the presence of asterisk.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static boolean parseAsterisk(String[] nameKeywords) {
        requireNonNull(nameKeywords);
        if (nameKeywords[0].compareTo("*") != 0) {
            return false;
        }
        return true;
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String theme} into usable form.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code theme} is invalid.
     */
    public static String parseTheme(String theme) throws ParseException {
        requireNonNull(theme);
        String trimmedTheme = theme.trim();
        if (!ThemeCommand.isValidTheme(trimmedTheme)) {
            throw new ParseException(ThemeCommand.MESSAGE_INVALID_THEME);
        }
        return trimmedTheme;
    }

    /**
     * Parses a {@code String department} into a {@code Department}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code department} is invalid.
     */
    public static Department parseDepartment(String department) throws ParseException {
        requireNonNull(department);
        String trimmedDepartment = department.trim();
        if (!Department.isValidDepartment(trimmedDepartment)) {
            throw new ParseException(Department.MESSAGE_CONSTRAINTS);
        }
        return new Department(trimmedDepartment);
    }

    /**
     * Parses a {@code String payroll} into a {@code Payroll}.
     * Leading and trailing whitespaces will be trimmed. Payroll consists of two integer values
     * for salary and monthly date of payment.
     *
     * @throws ParseException if the given {@code payroll} is invalid.
     */
    public static Payroll parsePayroll(String payroll) throws ParseException {
        requireNonNull(payroll);
        String trimmedPayroll = payroll.trim();

        if (!Payroll.isValidPayroll(trimmedPayroll)) {
            throw new ParseException(Payroll.MESSAGE_CONSTRAINTS);
        }
        String[] parts = payroll.split(" ");
        assert (parts.length == 2);
        int salary = Integer.parseInt(parts[0]);
        int dateOfPayment = Integer.parseInt(parts[1]);
        return new Payroll(salary, dateOfPayment);
    }

    /**
     * Parses a {@code String leaveCount} into a {@code LeaveCounter}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code leaveCount} is invalid.
     */
    public static LeaveCounter parseLeaveCount(Optional<String> leaveCount) throws ParseException {
        requireNonNull(leaveCount);
        Optional<String> trimmedLeaveCount = leaveCount.map(s -> s.trim());


        if (!LeaveCounter.isValidLeaveCount(trimmedLeaveCount.orElse("0"))) {
            throw new ParseException(LeaveCounter.MESSAGE_CONSTRAINTS);
        }
        return trimmedLeaveCount.map(c -> new LeaveCounter(Integer.parseInt(c))).orElseGet(() -> new LeaveCounter());
    }

    /**
     * Parses a {@code Optional<String> dateOfBirth} into a {@code Optional<LocalDate>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateOfBirth} is invalid.
     */
    public static Optional<LocalDate> parseDateOfBirth(Optional<String> dateOfBirth) throws ParseException {
        requireNonNull(dateOfBirth);
        Optional<LocalDate> localDate = dateOfBirth.map(date -> LocalDate.parse(date));
        return localDate;
    }

    /**
     * Parses a {@code Optional<String> dateOfJoining} into a {@code Optional<LocalDate>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateOfJoining} is invalid.
     */
    public static Optional<LocalDate> parseDateOfJoining(Optional<String> dateOfJoining) throws ParseException {
        requireNonNull(dateOfJoining);
        Optional<LocalDate> localDate = dateOfJoining.map(date -> LocalDate.parse(date));
        return localDate;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String filename} into a {@code String}.
     */
    public static String parseFileName(String filename) throws ParseException {
        requireNonNull(filename);
        String trimmedFileName = filename.trim();
        System.out.println(trimmedFileName);
        if (trimmedFileName.length() < 1) {
            throw new ParseException("Fail");
        }
        return trimmedFileName;
    }

    /**
     * Parses {@code String filename} into a {@code String}.
     */
    public static String parseExport(String filename) throws ParseException {
        requireNonNull(filename);
        String trimmedFileName = filename.trim();
        System.out.println(trimmedFileName);
        if (trimmedFileName.length() < 1) {
            throw new ParseException("Fail");
        }
        return trimmedFileName;
    }

}
