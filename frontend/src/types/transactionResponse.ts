export interface TransactionResponse {
  idTransaction: number;
  transactionType: string;
  date: Date;
  amount: number;
  description: string | null;
  category: string;
  originBank: string;
  destinationBank: string | null;
}
