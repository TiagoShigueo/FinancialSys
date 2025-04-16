export interface Transaction {
  // idTransaction: number;
  transactionType: string;
  date: Date;
  amount: number;
  description: string | null;
  category: string;
  originBankId: number;
  destinationBankId: number | null;
}
