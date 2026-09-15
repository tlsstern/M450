import { defineConfig } from '@playwright/test';

// frontend (ng serve) and backend have to be running before the test starts
export default defineConfig({
  testDir: './e2e',
  use: {
    baseURL: 'http://localhost:4200',
  },
});
