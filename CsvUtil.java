
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvUtil {

    public static void salvar(Object[] objetos, String nomeArquivo) {

        if (objetos == null) {
            return;
        }

        try (FileWriter writer = new FileWriter(nomeArquivo, StandardCharsets.UTF_8)) {

            if (objetos.length == 0) {
                writer.append(" ");
                return;
            }

            Class<?> clazz = objetos[0].getClass();
            Field[] fields = clazz.getDeclaredFields();

            // Cabeçalho
            for (int i = 0; i < fields.length; i++) {
                writer.append(fields[i].getName());
                if (i < fields.length - 1) {
                    writer.append(";");
                }
            }
            writer.append("\n");

            // Dados
            for (Object obj : objetos) {
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    Object valor = fields[i].get(obj);

                    if (valor != null && valor.getClass() == Date.class) {
                        valor = ((Date) valor).getTime();
                    }

                    writer.append(String.valueOf(valor));
                    if (i < fields.length - 1) {
                        writer.append(";");
                    }
                }
                writer.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T[] ler(String nomeArquivo, Class<T> entity) {
        List<T> lista = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo, StandardCharsets.UTF_8))) {

            String header = reader.readLine();

            if (header == null) {
                return null;
            }

            String[] nomesCampos = header.split(";");

            String linha;
            while ((linha = reader.readLine()) != null) {

                String[] valores = linha.split(";");
                T obj = entity.getDeclaredConstructor().newInstance();

                for (int i = 0; i < nomesCampos.length; i++) {
                    Field field = entity.getDeclaredField(nomesCampos[i]);
                    field.setAccessible(true);

                    Class<?> tipo = field.getType();
                    String valor = valores[i];

                    // Conversões básicas
                    if (tipo == int.class || tipo == Integer.class) {
                        field.set(obj, Integer.parseInt(valor));
                    } else if (tipo == double.class || tipo == Double.class) {
                        field.set(obj, Double.parseDouble(valor));
                    } else if (tipo == boolean.class || tipo == Boolean.class) {
                        field.set(obj, Boolean.parseBoolean(valor));
                    } else if (tipo == Date.class) {
                        field.set(obj, new Date(Long.parseLong(valor)));
                    } else {
                        field.set(obj, valor);
                    }
                }

                lista.add(obj);
            }

        } catch (FileNotFoundException e) {
            // arquivo não encontrado, cria um novo
            return (T[]) Array.newInstance(entity, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Converter List -> Array
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(entity, lista.size());
        return lista.toArray(array);
    }
}
