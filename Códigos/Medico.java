import java.time.LocalDate;
import java.util.Scanner;

public class Medico {
    private String nome;
    private String crm;
    private String especialidade;
    private String telefone;
    private String email;
    private String endereco;
    private LocalDate dataRegistroCrm;

    public Medico(String nome, String crm, String especialidade, String telefone, String email, String endereco, LocalDate dataRegistroCrm) {
        setNome(nome);
        setCrm(crm);
        this.especialidade = especialidade;
        setTelefone(telefone);
        setEmail(email);
        this.endereco = endereco;
        this.dataRegistroCrm = dataRegistroCrm;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        if (crm == null || crm.isEmpty()) {
            throw new IllegalArgumentException("CRM não pode ser vazio.");
        }
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataRegistroCrm() {
        return dataRegistroCrm;
    }

    public void setDataRegistroCrm(LocalDate dataRegistroCrm) {
        this.dataRegistroCrm = dataRegistroCrm;
    }

    @Override
    public String toString() {
        return "Médico{" +
                "nome='" + nome + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", dataRegistroCrm=" + dataRegistroCrm +
                '}';
    }

    public static boolean filtrarPorData(Medico medico, LocalDate dataInicio, LocalDate dataFim) {
        return !medico.getDataRegistroCrm().isBefore(dataInicio) && !medico.getDataRegistroCrm().isAfter(dataFim);
    }

    // Método para cadastrar um médico
    public static Medico cadastrarMedico(Scanner scanner) {
        System.out.print("Digite o nome do médico: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CRM do médico: ");
        String crm = scanner.nextLine();

        System.out.print("Digite a especialidade: ");
        String especialidade = scanner.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Digite a data de registro do CRM (AAAA-MM-DD): ");
        String dataRegistroCrmStr = scanner.nextLine();
        LocalDate dataRegistroCrm = LocalDate.parse(dataRegistroCrmStr);

        return new Medico(nome, crm, especialidade, telefone, email, endereco, dataRegistroCrm);
    }
}