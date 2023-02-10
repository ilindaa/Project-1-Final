% run plotter, salter first
close all

% programmer changes this: have correct salter#.csv
fid = fopen('salter1.csv', 'r');

header = fscanf(fid, '%s,%s', [2 1]);

A = fscanf(fid, '%f,%f', [2 maxXRange]);
A = A';

x = A(:,1);
y = A(:,2);

% 26.2 Statistics on Sliding Windows of Data (Octave)
% moving average over a window of data (movmean)
% programmer changes this:
temp = movmean(y, 25);
y = temp;
A(:,2) = y;

% print out the x and y values and plot
fprintf(fid, '%f,%f\n', A');
plot(x, y);
set(gca, "linewidth", 1, "fontsize", 15);
xlabel("x");
ylabel("y");
% programmer changes this: change Smoother title depending on salter#.csv
title("Smoother Graph 1");
grid on

% programmer changes this: write to smoother#.csv
fid2 = fopen('smoother1.csv', 'w');

% write the header to the CSV first
fprintf(fid2, '%s,%s\n', 'x', 'y');

% write the x and y values to the CSV
fprintf(fid2, '%f,%f\n', A');

fclose(fid);
fclose(fid2);
