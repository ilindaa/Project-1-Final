clear all, fclose all
fid = fopen('testing.csv', 'r');
% commas at the end of a line = not printed in command window
% don't add a space between %f,%f and %s,%s
header = fscanf(fid, '%s,%s', [2 1])
A = fscanf(fid, '%f,%f', [2 11])
% fid = fopen('testing.txt', 'r');
% A = fscanf(fid, '%f %f', [2 11]) - no comma between %f if .txt file
B = A'
fclose(fid);

x = B(:,1);
y = B(:,2);

plot(x,y);
set(gca, 'linewidth', 1, 'fontsize', 36)
xlabel('x');
ylabel('y');
grid on
% run "readfile" in command window
