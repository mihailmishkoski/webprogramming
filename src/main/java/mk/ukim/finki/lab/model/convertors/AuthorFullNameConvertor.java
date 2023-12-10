package mk.ukim.finki.lab.model.convertors;



import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mk.ukim.finki.lab.model.AuthorFullName;


@Converter
public class AuthorFullNameConvertor implements AttributeConverter<AuthorFullName, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(AuthorFullName fullname) {
        if (fullname == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (fullname.getSurname() != null && !fullname.getSurname()
                .isEmpty()) {
            sb.append(fullname.getSurname());
            sb.append(SEPARATOR);
        }

        if (fullname.getName() != null
                && !fullname.getName().isEmpty()) {
            sb.append(fullname.getName());
        }

        return sb.toString();
    }

    @Override
    public AuthorFullName convertToEntityAttribute(String dbAuthorFullName) {
        if (dbAuthorFullName == null || dbAuthorFullName.isEmpty()) {
            return null;
        }

        String[] pieces = dbAuthorFullName.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        AuthorFullName fullname = new AuthorFullName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbAuthorFullName.contains(SEPARATOR)) {
            fullname.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                fullname.setName(pieces[1]);
            }
        } else {
            fullname.setName(firstPiece);
        }

        return fullname;
    }
}

