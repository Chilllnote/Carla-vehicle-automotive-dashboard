import type { AxiosInstance } from "axios";
import axios from "axios";


class PythonService {
    private api: AxiosInstance;

    constructor() {
        this.api = axios.create({
            baseURL: 'http://localhost:8081',
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


    async startCarlaTest(port: number, town: string, vehicleType: string, x: number, y: number, z: number): Promise<string | null> {
        // 1. Create the data object (This is your JSON parameter)
        const testConfig = {
            port: 9000,
            town: "Town01",
            vehicle_type: "vehicle.tesla.model3",
            spawn_point: {
                x: 100.5,
                y: 200.0,
                z: 0.5
            }
        };
        
        const config = {
            port,
            town,
            vehicle_type: vehicleType,
            spawn_point: { x, y, z }
        };

        try {
            // Using this.api instead of fetch
            // Axios automatically stringifies the body and sets Content-Type
            const response = await this.api.post('/carla-testing', config);

            // Axios puts the response body in the .data property
            if (response.data && response.data.ws_url) {
                return response.data.ws_url;
            }
        } catch (error) {
            // Axios errors contain more detail than fetch errors
            if (axios.isAxiosError(error)) {
                console.error("FastAPI Error:", error.response?.data || error.message);
            } else {
                console.error("Failed to trigger CARLA:", error);
            }
        }
        return null;
    };
}

const pythonService = new PythonService();
export default pythonService;