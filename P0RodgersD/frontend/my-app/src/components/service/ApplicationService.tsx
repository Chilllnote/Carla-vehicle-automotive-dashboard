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
    private STORAGE_KEY = 'employee_metadata_key'; // For localStorage sync
    private currentUser: EmployeeMetadataDTO | null = null;
    
    constructor() {
        this.api = axios.create({
            baseURL: 'http://localhost:8080/api',
            headers: {
                'Content-Type': 'application/json',
            },
            // Use this if you need to send cookies/session with your Spring Boot CORS config
            withCredentials: true 
        });
    }


    //----------------------------------------------------------------
    // Authentication & User Metadata Sync Methods
    // ---------------------------------------------------------------

    // TO-DO: In a real app, you'd want to handle token refresh, error states, and edge cases more robustly.
    async getActiveUser(username: string): Promise<EmployeeMetadataDTO> {
        // 1. Check RAM
        if (this.currentUser && this.currentUser.username === username) {
            return this.currentUser;
        }

        // 2. If not in RAM, fetch from API
        try {
            const response = await this.api.get<EmployeeMetadataDTO>(
                `/employee-metadata-username`, 
                { params: { username } }
            );
            
            // 3. Save to BOTH Memory and LocalStorage
            this.currentUser = response.data;
            localStorage.setItem(this.STORAGE_KEY, JSON.stringify(response.data));
            
            return this.currentUser;
        } catch (error) {
            console.error("Auth sync failed", error);
            throw error;
        }
    }

    // 1. Save metadata after a successful login
    // TO-DO: This is a simplified example. In a real app, you'd handle authentication tokens, refresh logic, and error handling more robustly.
    async login(username: string, password: string): Promise<EmployeeMetadataDTO> {
        const data = await this.getByUsername(username);
        localStorage.setItem(this.USER_KEY, JSON.stringify(data));
        this.currentUser = data;
        return data;
    }

    // 2. Retrieve from storage (Synchronous - great for initial App state)
    getStoredUser(): EmployeeMetadataDTO | null {
        const saved = localStorage.getItem(this.USER_KEY);
        return saved ? JSON.parse(saved) : null;
    }

    // 3. Clear memory on logout
    logout(): void {
        localStorage.removeItem(this.USER_KEY);
        // Optional: Call backend logout endpoint here
    }

    /**
     * Fetches employee metadata by username
     * Usage: const data = await applicationService.getByUsername('donovr');
     */
    async getByUsername(username: string): Promise<EmployeeMetadataDTO> {
        try {
            const response: AxiosResponse<EmployeeMetadataDTO> = await this.api.get(
                `/employee-metadata-username`, 
                { params: { username } } // Sends as ?username=donovr
            );
            return response.data;
        } catch (error) {
            console.error(`Error fetching user ${username}:`, error);
            throw error;
        }
    }


    //----------------------------------------------------------------
    // Example API Methods for Dashboard Data
    // ---------------------------------------------------------------
    /**
     * Example: Fetch all algorithms for the dashboard
     */
    async getAllAlgorithms(): Promise<AlgorithmSummaryDTO[]> {
        const response = await this.api.get<AlgorithmSummaryDTO[]>('/algorithms');
        return response.data;
    }
}

// Export a single instance (Singleton)
const applicationService = new ApplicationService();
export default applicationService;