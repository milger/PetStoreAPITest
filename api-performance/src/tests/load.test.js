import Pets from "../requests/pets.request.js";

import {group} from 'k6';

export let options = {
  stages: [
    { duration: '30s', target: 5 },
    { duration: '1m', target: 10 },
    { duration: '30s', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(99)<2000'], // 99% of requests must complete below 2 sec
  },
};

export default function () {
  group('Pet API service', () => {
    Pets.findByStatus("available");
  });

}