import axios from "axios";

const API_URL = "http://localhost:8080/api/v1";

class EmployeeDataService {
  retrieveAllEmployees() {
    return axios.get(`${API_URL}/employees`);
  }

  addEmployee(employee) {
    //name, role, rank etc
    return axios.post(`${API_URL}/${employee}`);
  }

  updateCourse(employeeId) {
    return axios.put(`${API_URL}/${employeeId}`);
  }

  deleteEmployee(employeeId) {
    return axios.delete(`${API_URL}/${employeeId}`);
  }
}

export default new EmployeeDataService();
