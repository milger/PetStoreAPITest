import {check} from 'k6';
import http from 'k6/http';
import { PET_FIND_BY_STATUS_URL } from '../endpoints/pet.endpoint.js';
import { StatusCode } from '../utils/contants.js';

export default Pets = {

  findByStatus: (status) => {
    this.url = PET_FIND_BY_STATUS_URL + status;
    let response = http.get(url)
    check(response, {
      'status OK': () => response.status == StatusCode.OK
    });

  }
}