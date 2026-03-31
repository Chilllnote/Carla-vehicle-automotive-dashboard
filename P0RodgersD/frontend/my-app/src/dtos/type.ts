export interface EmployeeMetadataDTO {
    id?: string;
    name?: string;
    email?: string;
    username?: string;
    password?: string;
    department?: string;
    role?: string;
}

// Define it here
export type ms = number;

export type AlgoStats = {
  passRate: number;
  failRate: number;
  avgLatency: ms;
};

export type AIAlgo = { 
    id: string; 
    name: string; 
    category: string; 
    description: string; 
    stats: AlgoStats; 
};