import type { AxiosInstance } from "axios";
import axios from "axios";


class PythonService {
    private api: AxiosInstance;

    constructor() {
        this.api = axios.create({
            baseURL: 'http://localhost:8080/api/python',
            headers: {
                'Content-Type': 'application/json',
            },
            withCredentials: true 
        });
    }

    async executeCode(code: string) {
        try {
            const response = await this.api.post('/execute', { code });
            return response.data;
        } catch (error) {
            console.error('Error executing Python code:', error);
            throw error;
        }
    }
}
const pythonService = new PythonService();
export default pythonService;