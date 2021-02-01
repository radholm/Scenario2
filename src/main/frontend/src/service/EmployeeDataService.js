import axios from "axios";

const API_URL = "http://localhost:8080/api/v1";

class EmployeeDataService {
  retrieveAllEmployees(name) {
    return axios.get(`${API_URL}/employees`);
  }
}

export default new EmployeeDataService();
