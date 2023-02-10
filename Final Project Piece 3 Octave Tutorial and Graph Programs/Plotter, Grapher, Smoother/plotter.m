% programmer changes this: define ranges here
minXRange = 1;
maxXRange = 100;

% programmer changes this: plotter#.csv
fid = fopen('plotter1.csv', 'w');

% get the x values (number of rows)
x = (minXRange:maxXRange)';

% function is y = 6x+9 (y = mx + b)
y = (6*x+9);

% horizontally concatenate x and y
A = horzcat(x, y);

% write header to the CSV first
fprintf(fid, '%s,%s\n', 'x', 'y');

% write the x and y values to the CSV and plot
fprintf(fid, '%f,%f\n', A');
plot(x, y);
set(gca, "linewidth", 1, "fontsize", 15);
xlabel("x");
ylabel("y");
% programmer changes this: change plotter#.csv, change title
title("Plotter Graph 1");
grid on

fclose(fid);

