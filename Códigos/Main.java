import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Cadastrar médico");
            System.out.println("3 - Cadastrar medicamento");
            System.out.println("4 - Emitir receituário");
            System.out.println("5 - Filtrar paciente");
            System.out.println("6 - Sair");

            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Paciente.cadastrarPaciente(scanner);
                    break;
                case 2:
                    Medico.cadastrarMedico(scanner);
                    break;
                case 3:
                    Medicamento.cadastrarMedicamento(scanner);
                    break;
                case 4:
                    Receituario.emitirReceituario(scanner);
                    break;
                case 5:
                    Paciente.filtrarPaciente(scanner);
                    break;
                case 6:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}