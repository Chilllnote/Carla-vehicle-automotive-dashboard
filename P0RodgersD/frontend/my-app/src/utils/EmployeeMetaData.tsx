import axios, { type AxiosInstance } from 'axios';
import type { EmployeeMetadataDTO } from '../dtos/type';


const API_BASE_URL = 'http://localhost:8080/api';

export interface EmployeeMetadata {
    id: string;
    name: string;
    email: string;
    department: string;
    role: string;
    status: 'active' | 'inactive';
    createdAt: string;
    updatedAt: string;
}

class EmployeeMetadataService {
    private api: AxiosInstance;

    constructor(baseURL: string = API_BASE_URL) {
        this.api = axios.create({ baseURL });
    }

    // Create
    async create(data: Omit<EmployeeMetadata, 'id' | 'createdAt' | 'updatedAt'>) {
        return this.api.post<EmployeeMetadata>('/', data);
    }

    // Read
    async getAll() {
        return this.api.get<EmployeeMetadata[]>('/');
    }

    async getByUsername(username: string) {
        return (await this.api.get<EmployeeMetadataDTO>(`/employee-metadata-username?username=${username}`)).data;
    }

    // Update
    async update(id: string, data: Partial<EmployeeMetadata>) {
        return this.api.put<EmployeeMetadata>(`/${id}`, data);
    }

    // Delete
    async delete(id: string) {
        return this.api.delete(`/${id}`);
    }
}

export default new EmployeeMetadataService();