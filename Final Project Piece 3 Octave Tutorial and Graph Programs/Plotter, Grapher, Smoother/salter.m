% run plotter first
close all

% programmer changes this: have correct plotter#.csv
fid = fopen('plotter1.csv', 'r');

header = fscanf(fid, '%s,%s', [2 1]);

A = fscanf(fid, '%f,%f', [2 maxXRange]);
A = A';

% programmer changes this: randomization pick the ranges
randomMinRange = 25;
randomMaxRange = 60;

for i = 1:1:maxXRange
  % go through for loop, get a ran value add it to y matrix element for respective row
  ran = randi([randomMinRange, randomMaxRange]);
  temp = A(i, 2);
  A(i, 2) = temp + ran;
end

x = A(:,1);
y = A(:,2);

% print out the x and y values and plot
fprintf(fid, '%f,%f\n', A');
plot(x, y);
set(gca, "linewidth", 1, "fontsize", 15);
xlabel("x");
ylabel("y");
% programmer changes this: change Salter title depending on plotter#.csv
title("Salter Graph 1");
grid on

% programmer changes this: write to salter#.csv
fid2 = fopen('salter1.csv', 'w');

% write the header to the CSV first
fprintf(fid2, '%s,%s\n', 'x', 'y');

% write the x and y values to the CSV
fprintf(fid2, '%f,%f\n', A');

fclose(fid);
fclose(fid2);
