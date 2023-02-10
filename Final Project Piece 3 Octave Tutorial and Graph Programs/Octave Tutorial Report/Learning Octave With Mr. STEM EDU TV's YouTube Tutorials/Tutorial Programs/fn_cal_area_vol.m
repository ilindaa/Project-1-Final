function [A, V] = fn_cal_area_vol(r)
  % r is the radius of sphere
  % A is the surface area of sphere
  % V is the volume of sphere
  % A = 4*pi*r^2;
  A = fn_area_sphere(r);
  V = 4/3*pi*r^3;
  % Command Window: {Area, Vol] = fn_cal_area_vol(2)
end
