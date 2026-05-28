
public final class Validadores {

    public static String apenasNumeros(String texto) {
        String apenasNumeros = "";

        if (texto == null || texto.isEmpty()) {
            return "";
        }

        for (char c : texto.toCharArray()) {
            if (Character.isDigit(c)) {
                apenasNumeros += c;
            }
        }
        return apenasNumeros;
    }

    public static boolean numeroValido(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }

        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean todosIguais(String texto) {
        for (int i = 1; i < texto.length(); i++) {
            if (texto.charAt(i) != texto.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean cpfValido(String cpf) {
        cpf = apenasNumeros(cpf);

        if (cpf.length() != 11) {
            return false;
        }

        // 3. Bloqueia CPFs com todos os dígitos iguais (ex: 111.111.111-11)
        // Eles passam no teste matemático, mas são inválidos pela Receita Federal
        if (todosIguais(cpf)) {
            return false;
        }

        // 4. Cálculo do Primeiro Dígito Verificador
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            int num = Character.getNumericValue(cpf.charAt(i));
            soma += (num * peso);
            peso--;
        }

        int r = soma % 11;
        int digito1 = (r < 2) ? 0 : 11 - r;

        // Verifica se o primeiro dígito bate
        if (Character.getNumericValue(cpf.charAt(9)) != digito1) {
            return false;
        }

        // 5. Cálculo do Segundo Dígito Verificador
        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            int num = Character.getNumericValue(cpf.charAt(i));
            soma += (num * peso);
            peso--;
        }

        r = soma % 11;
        int digito2 = (r < 2) ? 0 : 11 - r;

        // Verifica se o segundo dígito bate
        return Character.getNumericValue(cpf.charAt(10)) == digito2;
    }

    public static boolean emailValido(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        int arrobaIndex = email.indexOf('@');
        int ultimoArrobaIndex = email.lastIndexOf('@');
        int pontoIndex = email.lastIndexOf('.');

        // 1. Deve ter exatamente um '@'
        // 2. O '@' não pode ser o primeiro caractere
        // 3. Deve ter um ponto após o '@'
        // 4. O ponto não pode ser o último caractere
        if (arrobaIndex <= 0 || arrobaIndex != ultimoArrobaIndex) {
            return false;
        }

        if (pontoIndex <= arrobaIndex + 1 || pontoIndex == email.length() - 1) {
            return false;
        }

        // Garante que não há espaços em branco
        for (char c : email.toCharArray()) {
            if (Character.isWhitespace(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean telefoneValido(String telefone) {

        telefone = apenasNumeros(telefone);

        if (telefone.length() < 10 || telefone.length() > 11) {
            return false;
        }

        if (telefone.length() == 11) {
            if (telefone.charAt(2) != '9') {
                return false;
            }
        }

        // 3. Bloqueia números com todos os dígitos iguais (ex: 1111111111)
        if (todosIguais(telefone)) {
            return false;
        }

        // 4. Validação do DDD (dois primeiros dígitos)
        // No Brasil o DDD não começa com 0; o segundo dígito também não é 0 (ex.: 11, 21, 31)
        char ddd1 = telefone.charAt(0);
        char ddd2 = telefone.charAt(1);

        if (ddd1 == '0' || ddd2 == '0') {
            return false;
        }

        return true;
    }
}
