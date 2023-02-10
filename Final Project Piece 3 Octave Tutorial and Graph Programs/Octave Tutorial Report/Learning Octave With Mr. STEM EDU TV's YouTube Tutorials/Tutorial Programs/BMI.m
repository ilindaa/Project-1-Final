height = [1.78 1.85 1.73 1.68, 1.81];
weight = [68 89 64 51 106];

% i = 1;
for i = [1 2 3 4 5]
  BMI(i) = weight(i)/height(i)^2;

  if BMI(i) < 18.5
    category = 'Underweight';
  elseif BMI(i) < 25
    category = 'Normal Weight';
   elseif BMI(i) < 30
    category = 'Overweight';
   else
    category = 'Obese';
  end

  fprintf('%d %.2f %d %.1f %s\n', i, height(i), weight(i), BMI(i), category);
end
