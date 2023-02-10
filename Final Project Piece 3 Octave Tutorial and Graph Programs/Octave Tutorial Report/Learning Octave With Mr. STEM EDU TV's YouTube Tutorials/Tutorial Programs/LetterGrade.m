score = input('Enter a Score (0 to 100):');
fprintf('Score=%d\n', score);
if score >= 90
  fprintf('Letter Grade is A\n');
elseif score >= 80
  fprintf('Letter Grade is B\n');
elseif score >= 70
  fprintf('Letter Grade is C\n');
else
  fprintf('Letter Grade is F\n');
end
