import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Receituario {
    private int id;
    private LocalDate dataEmissao;
    private int idMedico;
    private String status;
    private String observacoes;
    private List<ItemReceituario> itens;

    public Receituario(int id, LocalDate dataEmissao, int idMedico) {
        this.id = id;
        setDataEmissao(dataEmissao);
        this.idMedico = idMedico;
        this.status = "Emitido";
        this.itens = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        if (dataEmissao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de emissão não pode ser futura.");
        }
        this.dataEmissao = dataEmissao;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!status.equals("Emitido") && !status.equals("Cancelado")) {
            throw new IllegalArgumentException("Status inválido.");
        }
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<ItemReceituario> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void adicionarItem(ItemReceituario item) {
        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade de itens deve ser positiva.");
        }
        itens.add(item);
    }

    public double calcularValorTotal() {
        double valorTotal = 0;
        for (ItemReceituario item : itens) {
            valorTotal += item.getQuantidade() * item.getMedicamento().getPreco();
        }
        return valorTotal;
    }

    public void imprimir() {
        System.out.println("ID: " + id);
        System.out.println("Data de Emissão: " + dataEmissao);
        System.out.println("ID do Médico: " + idMedico);
        System.out.println("Status: " + status);
        System.out.println("Observações: " + observacoes);
        System.out.println("Itens do Receituário:");
        for (ItemReceituario item : itens) {
            System.out.println(item);
        }
        System.out.println("Valor Total: " + calcularValorTotal());
    }

    public boolean isAtivo() {
        return "Emitido".equals(status);
    }

    public void cancelar() {
        setStatus("Cancelado");
    }

    public ItemReceituario getItemByMedicamento(String nomeMedicamento) {
        for (ItemReceituario item : itens) {
            if (item.getMedicamento().getNome().equalsIgnoreCase(nomeMedicamento)) {
                return item;
            }
        }
        return null;
    }

    // Método para filtrar receituários por data de emissão
    public static boolean filtrarPorData(Receituario receituario, LocalDate dataInicio, LocalDate dataFim) {
        return !receituario.getDataEmissao().isBefore(dataInicio) && !receituario.getDataEmissao().isAfter(dataFim);
    }

    // Método para emitir um receituário e adicionar itens a ele
    public static Receituario emitirReceituario(int id, int idMedico, List<Medicamento> medicamentos, Scanner scanner) {
        Receituario receituario = new Receituario(id, LocalDate.now(), idMedico);

        while (true) {
            System.out.print("Digite o nome do medicamento (ou 'sair' para finalizar): ");
            String nomeMedicamento = scanner.nextLine();

            if (nomeMedicamento.equalsIgnoreCase("sair")) {
                break;
            }

            Medicamento medicamento = medicamentos.stream()
                    .filter(m -> m.getNome().equalsIgnoreCase(nomeMedicamento))
                    .findFirst()
                    .orElse(null);

            if (medicamento != null) {
                System.out.print("Digite a quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                ItemReceituario itemReceituario = new ItemReceituario(medicamento, quantidade);
                receituario.adicionarItem(itemReceituario);
                System.out.println("Item adicionado ao receituário.");
            } else {
                System.out.println("Medicamento não encontrado.");
            }
        }

        System.out.println("Receituário emitido com sucesso!");
        receituario.imprimir();
        return receituario;
    }
}