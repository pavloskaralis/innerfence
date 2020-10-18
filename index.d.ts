declare module "hungry-inner-fence" {

  export interface PaymentConfirmation {
      status: String,
      recordId: String,
      transactionId: String,
      amount: String,
      currency: String,
      cardType: String,
      redactedCardNumber: String,
  }

  export interface PaymentParams {
      address: String,
      amount: String,
      curreny: String,
      city: String,
      company: String,
      description: String, 
      email: String,
      firstName: String, 
      invoiceNumber: String,
      lastName: String,
      phone: String,
      state: String,
      zip: String,
      recordId: String, 
  }

  export class RNInnerFence {
      static paymentRequest(params: PaymentParams):Promise<PaymentConfirmation>;
  }

  export default RNInnerFence;

}