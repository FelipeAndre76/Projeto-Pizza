import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReaderDecorator reader = new BufferedReaderDecorator(isr);

            // usuário que escolha o tipo de pizza
            System.out.println("Escolha o tipo de pizza - (1) Queijo, (2) Pepperoni, (3) Frango: ");
            int pizzaChoice = Integer.parseInt(reader.readLine());

            Pizza pizza;
            if (pizzaChoice == 1) {
                pizza = new CheesePizza();
            } else if (pizzaChoice == 2) {
                pizza = new PepperoniPizza();
            } else if (pizzaChoice == 3) {
                pizza = new ChickenPizza();
            } else {
                System.out.println("Opção de pizza inválida.");
                return;
            }

            //  usuário que escolha o método de entrega
            System.out.println("Escolha o método de entrega - (1) Retirada em Loja, (2) Entrega: ");
            int deliveryChoice = Integer.parseInt(reader.readLine());

            DeliveryMethod deliveryMethod;
            if (deliveryChoice == 1) {
                deliveryMethod = new InPersonDelivery();
            } else if (deliveryChoice == 2) {
                deliveryMethod = new OnlineDelivery();
            } else {
                System.out.println("Opção de entrega inválida.");
                return;
            }

            //  usuário que insira o valor do pagamento
            System.out.println("Insira o valor do pagamento: ");
            int paymentAmount = Integer.parseInt(reader.readLine());

            PaymentAdapter paymentAdapter;
            
            //  usuário que escolha o método de pagamento
            System.out.println("Escolha o método de pagamento - (1) Cartão de Crédito, (2) Dinheiro, (3) Pix");
            int paymentChoice = Integer.parseInt(reader.readLine());

            if (paymentChoice == 1) {
                paymentAdapter = new CreditCardPaymentAdapter(new CreditCardPayment());
            } else if (paymentChoice == 2) {
                paymentAdapter = new MoneyPaymentAdapter(new MoneyPayment());
            } else if (paymentChoice == 3) {
                paymentAdapter = new PixPaymentAdapter(new PixPayment());
            } else {
                System.out.println("Opção de pagamento inválida.");
                return;
            }

            //  usuário que decida se deseja emitir a nota fiscal
            System.out.println("Deseja emitir a nota fiscal? (Digite 'Sim' ou 'Não'): ");
            String notaFiscalChoice = reader.readLine().trim();

            if (notaFiscalChoice.equalsIgnoreCase("Sim")) {
                System.out.println("Por favor, insira o CPF do cliente: ");
                String cpfCliente = reader.readLine();

                PizzaOrder pizzaOrder;

                // Cria o pedido de pizza com base na escolha do usuário
                if (pizzaChoice == 1) {
                    pizzaOrder = new CheesePizzaOrder(pizza, deliveryMethod, 1, cpfCliente);
                } else if (pizzaChoice == 2) {
                    pizzaOrder = new PepperoniPizzaOrder(pizza, deliveryMethod, 1, cpfCliente);
                } else if (pizzaChoice == 3) {
                    pizzaOrder = new ChickenPizzaOrder(pizza, deliveryMethod, 1, cpfCliente);
                } else {
                    System.out.println("Opção de pizza inválida.");
                    return;
                }

                // Realiza o pedido e emite a nota fiscal
                pizzaOrder.placeOrder(paymentAmount); // Passe o valor do pagamento como parâmetro

                // Realiza o pagamento
                paymentAdapter.pay(paymentAmount);
            } else {
                // Caso o cliente não queira emitir a nota fiscal
                PizzaOrder pizzaOrder;

                // Cria o pedido de pizza com base na escolha do usuário
                if (pizzaChoice == 1) {
                    pizzaOrder = new CheesePizzaOrder(pizza, deliveryMethod, 0, null);
                } else if (pizzaChoice == 2) {
                    pizzaOrder = new PepperoniPizzaOrder(pizza, deliveryMethod, 0, null);
                } else if (pizzaChoice == 3) {
                    pizzaOrder = new ChickenPizzaOrder(pizza, deliveryMethod, 0, null);
                } else {
                    System.out.println("Opção de pizza inválida.");
                    return;
                }

                // Realiza o pedido sem emitir a nota fiscal
                pizzaOrder.placeOrder(paymentAmount); // Passe o valor do pagamento como parâmetro

                // Realiza o pagamento
                paymentAdapter.pay(paymentAmount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
