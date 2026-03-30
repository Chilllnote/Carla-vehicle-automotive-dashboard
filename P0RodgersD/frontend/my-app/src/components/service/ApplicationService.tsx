import axios, { type AxiosInstance, type AxiosResponse } from 'axios';

/** * Define your DTO interfaces here or import them from a types file 
 */
export interface AlgorithmSummaryDTO {
    id: string;
    name: string;
    type: string;
    successRate: number;
}

export interface EmployeeMetadataDTO {
    id: string;
    name: string;
    email: string;
    username: string;
    department: string;
    role: string;
    algorithms: AlgorithmSummaryDTO[];
}

class ApplicationService {
    private api: AxiosInstance;
    private USER_KEY = 'employee_metadata';
    private currentUser: EmployeeMetadataDTO | null = null;

    constructor() {
        this.api = axios.create({
            baseURL: 'http://localhost:8080/api',
            headers: { 'Content-Type': 'application/json' },
            //withCredentials: true 
        });
    }

    async login(username: string, password: string): Promise<EmployeeMetadataDTO> {
        try {
            // 1. Fetch user by username and password
            console.log('Fetching user with username:', username);
            const user = await this.getAuth(username, password);
            
            console.log('Login successful, retrieved user:', user);
            // 2. Sync to Memory and LocalStorage
            this.currentUser = user;
            localStorage.setItem(this.USER_KEY, JSON.stringify(user));
            return user;
        } catch (error: any) {
            // Propagate the specific error message
            if (error.response?.status === 404) {
                throw new Error('User not found');
            }
            throw error;
        }
    }

    async getByUsername(username: string): Promise<EmployeeMetadataDTO> {
        const response = await this.api.get<EmployeeMetadataDTO>(
            `/employee-metadata-username`, 
            { params: { username } }
        );
        return response.data;
    }

    async getAuth(username: string, password: string): Promise<EmployeeMetadataDTO> {
        const response = await this.api.get<EmployeeMetadataDTO>(
            `/employee-auth`, 
            { params: { username, password } }
        );
        return response.data;
    }

    getStoredUser(): EmployeeMetadataDTO | null {
        if (this.currentUser) return this.currentUser;
        const saved = localStorage.getItem(this.USER_KEY);
        if (saved) {
            this.currentUser = JSON.parse(saved);
            return this.currentUser;
        }
        return null;
    }

    logout(): void {
        this.currentUser = null;
        localStorage.removeItem(this.USER_KEY);
    }
}

// Export a single instance (Singleton)
const applicationService = new ApplicationService();
export default applicationService;