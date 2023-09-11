import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// Decorator para leitura de teclado
class BufferedReaderDecorator extends BufferedReader {
    public BufferedReaderDecorator(InputStreamReader reader) {
        super(reader);
    }

    public String readLineWithPrompt(String prompt) throws IOException {
        System.out.print(prompt);
        return super.readLine();
    }
}

class Pedido {
    private String descricao;
    private double valor;

    public Pedido(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }
}

class NotaFiscal {
    private int numeroNota;
    private Date dataEmissao;
    private List<Pedido> pedidos = new ArrayList<>();
    private double valorTotal;
    private String cpfCliente;

    public NotaFiscal(int numeroNota, Date dataEmissao, String cpfCliente) {
        this.numeroNota = numeroNota;
        this.dataEmissao = dataEmissao;
        this.cpfCliente = cpfCliente;
        this.valorTotal = 0.0; // Inicialize o valor total como 0.0
    }

    public void adicionarPedido(String descricao, double valor) {
        Pedido pedido = new Pedido(descricao, valor);
        pedidos.add(pedido);
        valorTotal += valor; // Atualize o valor total com o valor do pedido
    }

    public void emitirNotaFiscal() {
        System.out.println("Nota fiscal emitida para CPF " + cpfCliente);
        System.out.println("Número da nota: " + numeroNota);
        System.out.println("Data de emissão: " + dataEmissao);
        System.out.println("Valor total: R$" + valorTotal);
        for (Pedido pedido : pedidos) {
            System.out.println("Itens da nota: " + pedido.getDescricao() + " R$" + pedido.getValor());
        }
    }
}

// Implementação da interface Device
interface Pizza {
    void prepare();
}

// Implementação de tipos de pizzas
class CheesePizza implements Pizza {
    public void prepare() {
        System.out.println("Preparando uma pizza de queijo.");
    }
}

class PepperoniPizza implements Pizza {
    public void prepare() {
        System.out.println("Preparando uma pizza de pepperoni.");
    }
}

class ChickenPizza implements Pizza {
    public void prepare() {
        System.out.println("Preparando uma pizza de frango.");
    }
}

// Implementação da interface de DeliveryMethod 
interface DeliveryMethod {
    void deliverPizza();
}

// Implementação de tipos de delivery methods
class InPersonDelivery implements DeliveryMethod {
    public void deliverPizza() {
        System.out.println("Retirada em Loja.");
    }
}

class OnlineDelivery implements DeliveryMethod {
    public void deliverPizza() {
        System.out.println("Entrega - (Motoboy).");
    }
}

// Classe abstrata que utiliza os devices e os delivery methods
abstract class PizzaOrder {
    protected Pizza pizza;
    protected DeliveryMethod deliveryMethod;
    protected int numeroNotaFiscal;
    protected String cpfCliente;

    public PizzaOrder(Pizza pizza, DeliveryMethod deliveryMethod, int numeroNotaFiscal, String cpfCliente) {
        this.pizza = pizza;
        this.deliveryMethod = deliveryMethod;
        this.numeroNotaFiscal = numeroNotaFiscal;
        this.cpfCliente = cpfCliente;
    }

    public abstract void placeOrder(double valorTotal); // Adicione o parâmetro valorTotal
}

// Implementação da PizzaOrder utilizando Bridge
class CheesePizzaOrder extends PizzaOrder {
    public CheesePizzaOrder(Pizza pizza, DeliveryMethod deliveryMethod, int numeroNotaFiscal, String cpfCliente) {
        super(pizza, deliveryMethod, numeroNotaFiscal, cpfCliente);
    }

    public void placeOrder(double valorTotal) { // Receba o valor total como parâmetro
        pizza.prepare();
        System.out.println("Pedido de pizza de queijo feito.");
        deliveryMethod.deliverPizza();

        // Adicionar um pedido à nota fiscal
        NotaFiscal notaFiscal = new NotaFiscal(numeroNotaFiscal, new Date(System.currentTimeMillis()), cpfCliente);
        notaFiscal.adicionarPedido("Pizza de Queijo", valorTotal); // Use o valor passado como parâmetro

        // Emitir a nota fiscal
        notaFiscal.emitirNotaFiscal();
    }
}

class PepperoniPizzaOrder extends PizzaOrder {
    public PepperoniPizzaOrder(Pizza pizza, DeliveryMethod deliveryMethod, int numeroNotaFiscal, String cpfCliente) {
        super(pizza, deliveryMethod, numeroNotaFiscal, cpfCliente);
    }

    public void placeOrder(double valorTotal) { // Receba o valor total como parâmetro
        pizza.prepare();
        System.out.println("Pedido de pizza de pepperoni feito.");
        deliveryMethod.deliverPizza();

         // Adicionar um pedido à nota fiscal
        NotaFiscal notaFiscal = new NotaFiscal(numeroNotaFiscal, new Date(System.currentTimeMillis()), cpfCliente);
        notaFiscal.adicionarPedido("Pizza de pepperoni", valorTotal); // Use o valor passado como parâmetro

        // Emitir a nota fiscal
        notaFiscal.emitirNotaFiscal();
    }
}

class ChickenPizzaOrder extends PizzaOrder {
    public ChickenPizzaOrder(Pizza pizza, DeliveryMethod deliveryMethod, int numeroNotaFiscal, String cpfCliente) {
        super(pizza, deliveryMethod, numeroNotaFiscal, cpfCliente);
    }

    public void placeOrder(double valorTotal) { // Receba o valor total como parâmetro
        pizza.prepare();
        System.out.println("Pedido de pizza de frango feito.");
        deliveryMethod.deliverPizza();

         // Adicionar um pedido à nota fiscal
        NotaFiscal notaFiscal = new NotaFiscal(numeroNotaFiscal, new Date(System.currentTimeMillis()), cpfCliente);
        notaFiscal.adicionarPedido("Pizza de frango", valorTotal); // Use o valor passado como parâmetro

        // Emitir a nota fiscal
        notaFiscal.emitirNotaFiscal();
    }
}

// Interface do PaymentAdapter
interface PaymentAdapter {
    void pay(int amount);
}

// Classes de diferentes formas de pagamento
class CreditCardPayment {
    void payWithCreditCard(int amount) {
        System.out.println("Pagamento de R$" + amount + " feito com cartão de crédito.");
    }
}

class MoneyPayment {
    void payMoney(int amount) {
        System.out.println("Pagamento de R$" + amount + " feito em dinheiro.");
    }
}

class PixPayment {
    void payPix(int amount) {
        System.out.println("Pagamento de R$" + amount + " feito com o Pix.");
    }
}

// Adapters para adaptar as formas de pagamento
class CreditCardPaymentAdapter implements PaymentAdapter {
    private CreditCardPayment payment;

    public CreditCardPaymentAdapter(CreditCardPayment payment) {
        this.payment = payment;
    }

    public void pay(int amount) {
        payment.payWithCreditCard(amount);
    }
}

class MoneyPaymentAdapter implements PaymentAdapter {
    private MoneyPayment payment;

    public MoneyPaymentAdapter(MoneyPayment payment) {
        this.payment = payment;
    }

    public void pay(int amount) {
        payment.payMoney(amount);
    }
}

class PixPaymentAdapter implements PaymentAdapter {
    private PixPayment payment;

    public PixPaymentAdapter(PixPayment payment) {
        this.payment = payment;
    }

    public void pay(int amount) {
        payment.payPix(amount);
    }
}
