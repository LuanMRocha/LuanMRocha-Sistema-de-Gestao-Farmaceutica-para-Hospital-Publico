import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Paciente {
    private String nome;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private String telefone;
    private String cpf;
    private List<Receituario> receituarios;
    private Map<String, String> alergias;

    public Paciente(String nome, LocalDate dataNascimento, Endereco endereco, String telefone, String cpf) {
        setNome(nome);
        setDataNascimento(dataNascimento);
        this.endereco = endereco;
        setTelefone(telefone);
        setCpf(cpf);
        this.receituarios = new ArrayList<>();
        this.alergias = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser futura.");
        }
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio.");
        }
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }
        this.cpf = cpf;
    }

    public List<Receituario> getReceituarios() {
        return Collections.unmodifiableList(receituarios);
    }

    public Map<String, String> getAlergias() {
        return Collections.unmodifiableMap(alergias);
    }

    public void adicionarReceituario(Receituario receituario) {
        receituarios.add(receituario);
    }

    public void adicionarAlergia(String alergeno, String detalhes) {
        alergias.put(alergeno, detalhes);
    }

    public boolean temAlergia(String alergeno) {
        return alergias.containsKey(alergeno);
    }

    public int calcularIdade() {
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    }

    public void imprimirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Data de Nascimento: " + dataNascimento + " (Idade: " + calcularIdade() + " anos)");
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);
        System.out.println("CPF: " + cpf);
        System.out.println("Alergias: " + (alergias.isEmpty() ? "Nenhuma" : alergias));
        System.out.println("Receituários:");
        if (receituarios.isEmpty()) {
            System.out.println("Nenhum receituário registrado.");
        } else {
            for (Receituario receituario : receituarios) {
                System.out.println(" - " + receituario);
            }
        }
    }

    public void imprimirInformacoesCompactas() {
        System.out.println("Nome: " + nome + ", CPF: " + cpf);
    }

    public static boolean filtrarPorData(Paciente paciente, LocalDate dataInicio, LocalDate dataFim) {
        return !paciente.getDataNascimento().isBefore(dataInicio) && !paciente.getDataNascimento().isAfter(dataFim);
    }

    // Método para cadastrar um paciente
    public static Paciente cadastrarPaciente(Scanner scanner) {
        System.out.print("Digite o nome do paciente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a data de nascimento do paciente (AAAA-MM-DD): ");
        String dataNascimentoStr = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);

        System.out.print("Digite o telefone do paciente: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o CPF do paciente (apenas números): ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o endereço do paciente: ");
        String endereco = scanner.nextLine(); // Assume que Endereco é uma classe e deve ser tratada separadamente.

        // Criando um objeto Endereco temporário para exemplo. Ajuste conforme necessário.
        Endereco enderecoPaciente = new Endereco(endereco); 

        return new Paciente(nome, dataNascimento, enderecoPaciente, telefone, cpf);
    }
}