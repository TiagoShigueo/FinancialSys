export interface ScheduledTransaction {
  idScheduledTransaction: number;
  recurenceType: string;
  paymentDate: Date;
  totalInstallments: number;
  amount: number;
  description: string | null;
  category: string;
  bankId: number;
}
