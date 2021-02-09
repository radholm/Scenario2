import axios from "axios";

const API_URL = "http://localhost:8080/api/v1";
const employeeEnum = "EMPLOYEE";
const managerEnum = "MANAGER";
const ceoEnum = "CEO";

class EmployeeDataService {
  retrieveAllEmployees() {
    return axios.get(`${API_URL}/${managerEnum}`);
  }

  retrieveEmployee(id) {
    return axios.get(`${API_URL}/employee/${id}`);
  }

  addEmployee(roleType, employee, superiorId) {
    return axios.post(`${API_URL}/${roleType}/${superiorId}`, employee);
  }

  updateEmployee(employeeId) {
    return axios.put(`${API_URL}/${employeeId}`);
  }

  deleteEmployee(employeeId) {
    return axios.delete(`${API_URL}/${employeeId}`);
  }
}

export default new EmployeeDataService();
