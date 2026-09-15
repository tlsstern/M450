import { test, expect } from '@playwright/test';

test('add student and see it in the list', async ({ page }) => {
  await page.goto('/');

  await page.getByRole('link', { name: 'Add Students' }).click();
  await page.locator('#name').fill('Thomas');
  await page.locator('#email').fill('thomas@tbz.ch');
  await page.getByRole('button', { name: 'Submit' }).click();

  // after submit the app navigates to the student list
  await expect(page).toHaveURL(/\/students/);
  // the new student is the last row in the table
  const lastRow = page.locator('tbody tr').last();
  await expect(lastRow).toContainText('Thomas');
  await expect(lastRow).toContainText('thomas@tbz.ch');
});
